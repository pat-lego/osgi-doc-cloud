package io.github.patlego.dc.core;

import io.github.patlego.dc.core.exceptions.DocCloudException;

public interface DoCService extends DocAuthentication {

    public <T> T invoke() throws DocCloudException; 
    
}
