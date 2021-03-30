package com.bf1.experimentation;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PipedWriter;
import java.io.Reader;

public class PipedRead extends Reader {
    boolean closedByWriter;
    boolean closedByReader;
    boolean connected;
    Thread readSide;
    Thread writeSide;
    private static final int DEFAULT_PIPE_SIZE = 1024;
    char[] buffer;
    int in;
    int out;

    public PipedRead(PipedWrite src) throws IOException {
        this(src, 1024);
    }

    public PipedRead(PipedWrite src, int pipeSize) throws IOException {
        this.closedByWriter = false;
        this.closedByReader = false;
        this.connected = false;
        this.in = -1;
        this.out = 0;
        this.initPipe(pipeSize);
        this.connect(src);
    }

    public PipedRead() {
        this.closedByWriter = false;
        this.closedByReader = false;
        this.connected = false;
        this.in = -1;
        this.out = 0;
        this.initPipe(1024);
    }

    public PipedRead(int pipeSize) {
        this.closedByWriter = false;
        this.closedByReader = false;
        this.connected = false;
        this.in = -1;
        this.out = 0;
        this.initPipe(pipeSize);
    }

    private void initPipe(int pipeSize) {
        if (pipeSize <= 0) {
            throw new IllegalArgumentException("Pipe size <= 0");
        } else {
            this.buffer = new char[pipeSize];
        }
    }

    public void connect(PipedWrite src) throws IOException {
        src.connect(this);
    }

    synchronized void receive(int c) throws IOException {
        if (!this.connected) {
            throw new IOException("Pipe not connected");
        } else if (!this.closedByWriter && !this.closedByReader) {
            if (this.readSide != null && !this.readSide.isAlive()) {
                throw new IOException("Read end dead");
            } else {
                this.writeSide = Thread.currentThread();

                while (this.in == this.out) {
                    if (this.readSide != null && !this.readSide.isAlive()) {
                        throw new IOException("Pipe broken");
                    }

                    this.notifyAll();

                    try {
                        this.wait(1000L);
                    } catch (InterruptedException var3) {
                        throw new InterruptedIOException();
                    }
                }

                if (this.in < 0) {
                    this.in = 0;
                    this.out = 0;
                }

                this.buffer[this.in++] = (char) c;
                if (this.in >= this.buffer.length) {
                    this.in = 0;
                }

            }
        } else {
            throw new IOException("Pipe closed");
        }
    }

    synchronized void receive(char[] c, int off, int len) throws IOException {
        while (true) {
            --len;
            if (len < 0) {
                return;
            }

            this.receive(c[off++]);
        }
    }

    synchronized void receivedLast() {
        this.closedByWriter = true;
        this.notifyAll();
    }

    public synchronized int read() throws IOException {
        if (!this.connected) {
            throw new IOException("Pipe not connected");
        } else if (this.closedByReader) {
            throw new IOException("Pipe closed");
        } else if (this.writeSide != null && !this.writeSide.isAlive() && !this.closedByWriter && this.in < 0) {
            throw new IOException("Write end dead");
        } else {
            this.readSide = Thread.currentThread();
            int trials = 2;

            while (this.in < 0) {
                if (this.closedByWriter) {
                    return -1;
                }

                if (this.writeSide != null && !this.writeSide.isAlive()) {
                    --trials;
                    if (trials < 0) {
                        throw new IOException("Pipe broken");
                    }
                }

                this.notifyAll();

                try {
                    this.wait(1000L);
                } catch (InterruptedException var3) {
                    throw new InterruptedIOException();
                }
            }

            int ret = this.buffer[this.out++];
            if (this.out >= this.buffer.length) {
                this.out = 0;
            }

            if (this.in == this.out) {
                this.in = -1;
            }

            return ret;
        }
    }

    public synchronized int read(char[] cbuf, int off, int len) throws IOException {
        if (!this.connected) {
            throw new IOException("Pipe not connected");
        } else if (this.closedByReader) {
            throw new IOException("Pipe closed");
        } else if (this.writeSide != null && !this.writeSide.isAlive() && !this.closedByWriter && this.in < 0) {
            throw new IOException("Write end dead");
        } else if (off >= 0 && off <= cbuf.length && len >= 0 && off + len <= cbuf.length && off + len >= 0) {
            if (len == 0) {
                return 0;
            } else {
                int c = this.read();
                if (c < 0) {
                    return -1;
                } else {
                    cbuf[off] = (char) c;
                    int rlen = 1;

                    while (this.in >= 0) {
                        --len;
                        if (len <= 0) {
                            break;
                        }

                        cbuf[off + rlen] = this.buffer[this.out++];
                        ++rlen;
                        if (this.out >= this.buffer.length) {
                            this.out = 0;
                        }

                        if (this.in == this.out) {
                            this.in = -1;
                        }
                    }

                    return rlen;
                }
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public synchronized boolean ready() throws IOException {
        if (!this.connected) {
            throw new IOException("Pipe not connected");
        } else if (this.closedByReader) {
            throw new IOException("Pipe closed");
        } else if (this.writeSide != null && !this.writeSide.isAlive() && !this.closedByWriter && this.in < 0) {
            throw new IOException("Write end dead");
        } else {
            return this.in >= 0;
        }
    }

    public void close() throws IOException {
        this.in = -1;
        this.closedByReader = true;
    }
}