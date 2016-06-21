package com.eugenes.functional.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BufferSupport {

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Clipboard clipBoard;

    public String getBufferContents() throws UnsupportedFlavorException, IOException {

        clipBoard = toolkit.getSystemClipboard();
        return (String) clipBoard.getData(DataFlavor.stringFlavor);

    }

}
