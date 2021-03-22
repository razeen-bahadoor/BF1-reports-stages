package com.bf1.grammarsuite;

import java.io.IOException;

import java.io.Writer;

public class StringWrite extends Writer {
    private StringBuffer buf;

    public StringWrite() {
        this.buf = new StringBuffer();
        this.lock = this.buf;
    }

    public StringWrite(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Negative buffer size");
        } else {
            this.buf = new StringBuffer(initialSize);
            this.lock = this.buf;
        }
    }

    public void write(int c) {
        this.buf.append((char)c);
    }

    public void write(char[] cbuf, int off, int len) {
        if (off >= 0 && off <= cbuf.length && len >= 0 && off + len <= cbuf.length && off + len >= 0) {
            if (len != 0) {
                this.buf.append(cbuf, off, len);
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void write(String str) {
        this.buf.append(str);
    }

    public void write(String str, int off, int len) {
        this.buf.append(str, off, off + len);
    }

    public StringWrite append(CharSequence csq) {
        this.write(String.valueOf(csq));
        return this;
    }

    public StringWrite append(CharSequence csq, int start, int end) {
        if (csq == null) {
            csq = "null";
        }

        return this.append(((CharSequence)csq).subSequence(start, end));
    }

    public StringWrite append(char c) {
        this.write(c);
        return this;
    }

    public String toString() {
        return this.buf.toString();
    }

    public StringBuffer getBuffer() {
        return this.buf;
    }

    public void flush() {
    }

    public void close() throws IOException {
    }
}
