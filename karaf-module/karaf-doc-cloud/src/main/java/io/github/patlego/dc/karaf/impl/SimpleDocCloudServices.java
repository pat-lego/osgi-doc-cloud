package io.github.patlego.dc.karaf.impl;

import java.io.OutputStream;

import org.osgi.service.component.annotations.Component;

import io.github.patlego.dc.core.exceptions.DocCloudException;
import io.github.patlego.dc.core.merge.MergeDocumentToPDF;
import io.github.patlego.dc.karaf.DocCloudServices;

@Component(service = DocCloudServices.class,immediate = true)
public class SimpleDocCloudServices implements DocCloudServices {

    @Override
    public OutputStream mergeDocument() throws DocCloudException {
        return new MergeDocumentToPDF().invoke();
    }
}
