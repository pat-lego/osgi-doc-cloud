package io.github.patlego.dc.karaf.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.nimbusds.jose.util.StandardCharset;

import org.apache.commons.io.IOUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import io.github.patlego.dc.core.exceptions.DocCloudException;
import io.github.patlego.dc.core.merge.MergeDocumentToPDF;
import io.github.patlego.dc.karaf.DocCloudServices;

@Component(service = DocCloudServices.class,immediate = true)
public class SimpleDocCloudServices implements DocCloudServices {

    // TODO set this
    private String path;

    @Override
    public OutputStream mergeDocument() throws DocCloudException {
        return new MergeDocumentToPDF().invoke();
    }

    @Activate
    public void activate() throws Exception {
        ByteArrayOutputStream out = (ByteArrayOutputStream) mergeDocument();
        byte[] result = out.toByteArray();

        Files.write(Paths.get(String.format("%s/result.pdf", path)), result);
    }
    
}
