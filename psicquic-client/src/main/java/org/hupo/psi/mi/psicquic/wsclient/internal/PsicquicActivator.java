/**
 * Copyright 2009 The European Bioinformatics Institute, and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hupo.psi.mi.psicquic.wsclient.internal;

import java.util.Dictionary;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.hupo.psi.mi.psicquic.wsclient.PsicquicClient;
import org.hupo.psi.mi.psicquic.wsclient.UniversalPsicquicClient;
import psidev.psi.mi.tab.model.BinaryInteraction;
import psidev.psi.mi.search.SearchResult;


/**
 * Extension of the default OSGi bundle activator
 */
public final class PsicquicActivator implements BundleActivator {
    
    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start(BundleContext bc) throws Exception {
        System.out.println("STARTING PSICQUIC Client");

        Dictionary props = new Properties();
        // add specific service properties here...

        //System.out.println( "REGISTER org.hupo.psi.mi.psicquic2.ExampleService" );

        // Register our example service implementation in the OSGi service registry
        //bc.registerService( ExampleService.class.getName(), new ExampleServiceImpl(), props );

        UniversalPsicquicClient client = new UniversalPsicquicClient("http://www.ebi.ac.uk/intact/psicquic/webservices/psicquic");
        System.out.println("CLIENT INSTANCE: "+client);
        final SearchResult<BinaryInteraction> searchResult = client.getByQuery("*", 0, 0);
        System.out.println("Found:" +searchResult.getTotalCount());
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop(BundleContext bc) throws Exception {
        System.out.println("STOPPING PSICQUIC Client");

        // no need to unregister our service - the OSGi framework handles it for us
    }
}

