package tech.bedrockstreaming.iabtcf.extras.jackson;

/*-
 * #%L
 * IAB TCF Java GVL and CMP List Jackson
 * %%
 * Copyright (C) 2020 IAB Technology Laboratory, Inc
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import tech.bedrockstreaming.iabtcf.extras.jackson.cmp.Cmp;
import tech.bedrockstreaming.iabtcf.extras.jackson.cmp.CmpList;
import tech.bedrockstreaming.iabtcf.extras.jackson.gvl.Feature;
import tech.bedrockstreaming.iabtcf.extras.jackson.gvl.Gvl;
import tech.bedrockstreaming.iabtcf.extras.jackson.gvl.Overflow;
import tech.bedrockstreaming.iabtcf.extras.jackson.gvl.Purpose;
import tech.bedrockstreaming.iabtcf.extras.jackson.gvl.SpecialFeature;
import tech.bedrockstreaming.iabtcf.extras.jackson.gvl.SpecialPurpose;
import tech.bedrockstreaming.iabtcf.extras.jackson.gvl.Stack;
import tech.bedrockstreaming.iabtcf.extras.jackson.gvl.Vendor;

public class Loader {
    private ObjectMapper objectMapper = new ObjectMapper();

    public Loader() {
        SimpleModule module = new SimpleModule();

        SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();
        resolver.addMapping(tech.bedrockstreaming.iabtcf.extras.gvl.Gvl.class, Gvl.class);
        resolver.addMapping(tech.bedrockstreaming.iabtcf.extras.gvl.Feature.class, Feature.class);
        resolver.addMapping(tech.bedrockstreaming.iabtcf.extras.gvl.Overflow.class, Overflow.class);
        resolver.addMapping(tech.bedrockstreaming.iabtcf.extras.gvl.Purpose.class, Purpose.class);
        resolver.addMapping(tech.bedrockstreaming.iabtcf.extras.gvl.SpecialFeature.class, SpecialFeature.class);
        resolver.addMapping(tech.bedrockstreaming.iabtcf.extras.gvl.SpecialPurpose.class, SpecialPurpose.class);
        resolver.addMapping(tech.bedrockstreaming.iabtcf.extras.gvl.Stack.class, Stack.class);
        resolver.addMapping(tech.bedrockstreaming.iabtcf.extras.gvl.Vendor.class, Vendor.class);

        resolver.addMapping(tech.bedrockstreaming.iabtcf.extras.cmp.Cmp.class, Cmp.class);
        resolver.addMapping(tech.bedrockstreaming.iabtcf.extras.cmp.CmpList.class, CmpList.class);

        module.setAbstractTypes(resolver);
        objectMapper.registerModule(module);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Gets the global vendor list json from the specified InputStream
     *
     * @param content {@link InputStream} of GVL json content
     * @return {@link tech.bedrockstreaming.iabtcf.extras.gvl.Gvl} object
     */
    public tech.bedrockstreaming.iabtcf.extras.gvl.Gvl globalVendorList(InputStream content) throws IOException {
        return objectMapper.readValue(content, tech.bedrockstreaming.iabtcf.extras.gvl.Gvl.class);
    }

    /**
     * Gets the global vendor list json using a json string
     *
     * @param json the gvl json as a string
     * @return {@link tech.bedrockstreaming.iabtcf.extras.gvl.Gvl} object
     */
    public tech.bedrockstreaming.iabtcf.extras.gvl.Gvl globalVendorList(String json) throws IOException {
        return objectMapper.readValue(json, tech.bedrockstreaming.iabtcf.extras.gvl.Gvl.class);
    }

    /**
     * Gets the CMP list from the specified InputStream
     *
     * @param content {@link InputStream} of CMP List json content
     * @return {@link tech.bedrockstreaming.iabtcf.extras.cmp.CmpList} object
     */
    public tech.bedrockstreaming.iabtcf.extras.cmp.CmpList cmpList(InputStream content) throws IOException {
        return objectMapper.readValue(content, tech.bedrockstreaming.iabtcf.extras.cmp.CmpList.class);
    }

    /**
     * Gets the CMP list from the specified InputStream
     *
     * @param json the CMP List json as a string
     * @return {@link tech.bedrockstreaming.iabtcf.extras.cmp.CmpList} object
     */
    public tech.bedrockstreaming.iabtcf.extras.cmp.CmpList cmpList(String json) throws IOException {
        return objectMapper.readValue(json, tech.bedrockstreaming.iabtcf.extras.cmp.CmpList.class);
    }
}
