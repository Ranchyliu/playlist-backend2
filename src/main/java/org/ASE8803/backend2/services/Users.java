/*
 * Copyright (c) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not  use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.ASE8803.backend2.services;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import org.ASE8803.backend2.database.Connector;
import org.ASE8803.backend2.model.Message;
import org.ASE8803.backend2.model.User;


import java.util.List;

/** The Echo API which Endpoints will be exposing. */
// [START echo_api_annotation]
@Api(
        name = "users",
        version = "v1",
        namespace =
        @ApiNamespace(
                ownerDomain = "org.ASE8803.backend2",
                ownerName = "org.ASE8803.backend2",
                packagePath = ""
        )

)
// [END echo_api_annotation]
public class Users {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Message login(@Named("email") @Nullable String email, @Named("password") @Nullable String password) {
        Message m = new Message();
        List<String> result;
        Connector con = new Connector();
        result = con.login(email, password);
        con.close();
        if (result.size() <= 1) {
            m.setMessage("404");
        } else {
            m.setMessage("200");
        }
        return m;
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST
    )
    public Message register(@Named("email") @Nullable String email,
                            @Named("password") @Nullable String password,
                            @Named("username") @Nullable String username,
                            @Named("lastname") @Nullable String lastname,
                            @Named("firstname") @Nullable String firstname) {
        Message m = new Message();
        Connector con = new Connector();
        boolean s = con.register(email, password, username, lastname, firstname);
        con.close();
        if (s) {
            m.setMessage("200");
        } else {
            m.setMessage("404");
        }
        return m;
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "getInfo",
            path = "getInfo"
    )
    public User getInfo(@Named("email") @Nullable String email) {
        List<String> result;
        User user = new User();
        Message m = new Message();
        Connector con = new Connector();
        result = con.getInfo(email);
        con.close();
        if (result.size() <= 1) {
            user.setHasuser(false);
        } else {
            user.setHasuser(true);
            user.setEmail(result.get(0));
            user.setUsername(result.get(1));
            user.setFirstname(result.get(2));
            user.setLastname(result.get(3));
        }
        return user;
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE
    )
    public Message delete(@Named("email") @Nullable String email) {
        Message m = new Message();
        Connector con = new Connector();
        boolean s = con.delete(email);
        con.close();
        if (s) {
            m.setMessage("200");
        } else {
            m.setMessage("404");
        }
        return m;
    }
}
