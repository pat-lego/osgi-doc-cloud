package io.github.patlego.dc.core;

import com.adobe.pdfservices.operation.auth.Credentials;

public interface DocAuthentication {
    public Credentials getCredentials() throws Exception;
}
