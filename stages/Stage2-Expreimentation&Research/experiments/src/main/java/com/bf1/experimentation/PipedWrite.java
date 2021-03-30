package com.bf1.experimentation;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Writer;

public class PipedWrite extends Writer {
    private PipedRead sink;
    private boolean closed = false;

    public PipedWrite(PipedRead snk) throws IOException {
        this.connect(snk);
    }

    public PipedWrite() {
    }

    public synchronized void connect(PipedRead snk) throws IOException {
        if (snk == null) {
            throw new NullPointerException();
        } else if (this.sink == null && !snk.connected) {
            if (!snk.closedByReader && !this.closed) {
                this.sink = snk;
                snk.in = -1;
                snk.out = 0;
                snk.connected = true;
            } else {
                throw new IOException("Pipe closed");
            }
        } else {
            throw new IOException("Already connected");
        }
    }

    public void write(int c) throws IOException {
        if (this.sink == null) {
            throw new IOException("Pipe not connected");
        } else {
            this.sink.receive(c);
        }
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        if (this.sink == null) {
            throw new IOException("Pipe not connected");
        } else if ((off | len | off + len | cbuf.length - (off + len)) < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            this.sink.receive(cbuf, off, len);
        }
    }

    public synchronized void flush() throws IOException {
        if (this.sink != null) {
            if (this.sink.closedByReader || this.closed) {
                throw new IOException("Pipe closed");
            }

            synchronized(this.sink) {
                this.sink.notifyAll();
            }
        }

    }

    public void close() throws IOException {
        this.closed = true;
        if (this.sink != null) {
            this.sink.receivedLast();
        }

    }
}
