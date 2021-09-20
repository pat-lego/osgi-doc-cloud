/*
 * Copyright 2021 Adobe
 * All Rights Reserved.
 *
 * NOTICE: Adobe permits you to use, modify, and distribute this file in
 * accordance with the terms of the Adobe license agreement accompanying
 * it. If you have received this file from a source other than Adobe,
 * then your use, modification, or distribution of it requires the prior
 * written permission of Adobe.
 */

package io.github.patlego.dc.core.merge;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.auth.Credentials;
import com.adobe.pdfservices.operation.exception.SdkException;
import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.exception.ServiceUsageException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.DocumentMergeOperation;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.DocumentMergeOptions;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.OutputFormat;
import com.nimbusds.jose.util.StandardCharset;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import io.github.patlego.dc.core.DoCService;
import io.github.patlego.dc.core.exceptions.DocCloudException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This sample illustrates how to merge the Word based document template with the input JSON data to generate
 * the output document in the PDF format.
 * <p>
 * To know more about document generation and document templates, please see the <a href="http://www.adobe.com/go/dcdocgen_overview_doc">documentation</a>
 * <p>
 * Refer to README.md for instructions on how to run the samples.
 */
public class MergeDocumentToPDF implements DoCService {

    public OutputStream invoke() throws DocCloudException {

        try {

            OutputStream out = new ByteArrayOutputStream();

            JSONObject config = new JSONObject(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("pdfservices-api-credentials.json"), StandardCharset.UTF_8));

            // Initial setup, create credentials instance.
            Credentials credentials = Credentials.serviceAccountCredentialsBuilder()
                    .withAccountId(config.getJSONObject("service_account_credentials").getString("account_id"))
                    .withClientId(config.getJSONObject("client_credentials").getString("client_id"))
                    .withClientSecret(config.getJSONObject("client_credentials").getString("client_secret"))
                    .withOrganizationId(config.getJSONObject("service_account_credentials").getString("organization_id"))
                    .withPrivateKey(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("private.key"), StandardCharset.UTF_8))
                    .build();

            // Setup input data for the document merge process
            String content = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("salesOrder.json"), StandardCharset.UTF_8);
            JSONObject jsonDataForMerge = new JSONObject(content);
            
            // Create an ExecutionContext using credentials.
            ExecutionContext executionContext = ExecutionContext.create(credentials);

            //Create a new DocumentMergeOptions instance
            DocumentMergeOptions documentMergeOptions = new DocumentMergeOptions(jsonDataForMerge, OutputFormat.PDF);

            // Create a new DocumentMergeOperation instance with the DocumentMergeOptions instance
            DocumentMergeOperation documentMergeOperation = DocumentMergeOperation.createNew(documentMergeOptions);

            // Set the operation input document template from a source file.
            FileRef documentTemplate = FileRef.createFromStream(this.getClass().getClassLoader().getResourceAsStream("salesOrderTemplate.docx"), "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            documentMergeOperation.setInput(documentTemplate);

            // Execute the operation
            FileRef result = documentMergeOperation.execute(executionContext);

            // Save the result to the specified location.
            result.saveAs(out);

            return out;

        } catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
            throw new DocCloudException(ex.getMessage(), ex);
        }
    }
}
