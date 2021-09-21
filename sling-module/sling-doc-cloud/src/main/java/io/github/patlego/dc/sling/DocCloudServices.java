package io.github.patlego.dc.sling;

import java.io.OutputStream;

import io.github.patlego.dc.core.exceptions.DocCloudException;

public interface DocCloudServices {

    public OutputStream mergeDocument() throws DocCloudException;
    
}
