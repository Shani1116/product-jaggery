/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.jaggery.integration.tests.hostObjects.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jaggeryjs.integration.common.utils.JaggeryIntegrationTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.carbon.integration.framework.ClientConnectionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Test cases for Request Object
 */
public class RequestObjectTestCase extends JaggeryIntegrationTest {

    private static final Log log = LogFactory.getLog(RequestObjectTestCase.class);

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        try {
            init();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test(groups = {"jaggery"}, description = "Test request object")
    public void testRequest() {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        try {
            URL jaggeryURL = new URL(webAppURL + "/testapp/request.jag?param=test");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            finalOutput = in.readLine();
            in.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            assertNotNull(finalOutput, "Result cannot be null");
        }
    }

    @Test(groups = {"jaggery"}, description = "Test request object params")
    public void testReadRequestParams() {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        try {
            URL jaggeryURL = new URL(webAppURL + "/testapp/request.jag?param=test");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            finalOutput = in.readLine();
            in.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            assertEquals(finalOutput, "Param : test");
        }
    }

    @Test(groups = {"jaggery"}, description = "Test request object for Local")
    public void testReadLocalAllRequestParams() {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        try {
            URL jaggeryURL = new URL(webAppURL + "/testapp/request.jag?param=getAllLocales");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            finalOutput = in.readLine();
            in.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            assertEquals(finalOutput, "getAllLocales true");
        }
    }

    @Test(groups = {"jaggery"}, description = "Test request object for Local")
    public void testReadLocalRequestParams() {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        try {
            URL jaggeryURL = new URL(webAppURL + "/testapp/request.jag?param=getLocale");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            finalOutput = in.readLine();
            in.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            assertEquals(finalOutput, "en");
        }
    }

    @Test(groups = {"jaggery"}, description = "Test request object ")
    public void testReadRequest() {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        try {
            URL jaggeryURL = new URL(webAppURL + "/testapp/request.jag?test=hi");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            finalOutput = in.readLine();
            in.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            assertEquals(
                    finalOutput,
                    "Method : GET, Protocol : HTTP/1.1, QueryString : test=hi,"
                            + " URI : /testapp/request.jag, URL : http://localhost:9763/testapp/request.jag,"
                            + " LocalPort : 9763, ContentLength : -1, ContextPath : /testapp");
        }
    }

    @Test(groups = {"jaggery"}, description = "Test request object for getMappedPath")
    public void testgetMappedPathRequestParams() {
        ClientConnectionUtil.waitForPort(9763);
        String finalOutput = null;
        try {
            URL jaggeryURL = new URL(webAppURL + "/testapp/request.jag?param=getMappedPath");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            finalOutput = in.readLine();
            in.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            assertEquals(finalOutput, "getMappedPath : /request.jag");
        }
    }

    @Test(groups = {"jaggery"}, description = "Test request object for getAttribute")
    public void testGetAttribute() {
        ClientConnectionUtil.waitForPort(9443);
        BufferedReader in = null;
        String finalOutput = null;
        try {
            URL jaggeryURL = new URL(webAppURLHttps + "/testapp/request.jag?param=getAttribute");
            URLConnection jaggeryServerConnection = jaggeryURL.openConnection();
            in = new BufferedReader(new InputStreamReader(jaggeryServerConnection.getInputStream()));
            finalOutput = in.readLine();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            assertNotNull(finalOutput, "Attribute cannot be null");
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

}