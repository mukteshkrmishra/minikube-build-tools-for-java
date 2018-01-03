/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.cloud.tools.crepecake.registry;

import com.google.api.client.http.HttpMethods;
import com.google.cloud.tools.crepecake.http.Request;
import com.google.cloud.tools.crepecake.http.Response;
import com.google.cloud.tools.crepecake.image.json.V22ManifestTemplate;
import com.google.cloud.tools.crepecake.json.JsonTemplateMapper;

/** Pushes an image's manifest. */
class ManifestPusher implements RegistryEndpointProvider {

  private final V22ManifestTemplate manifestTemplate;
  private final String imageTag;

  ManifestPusher(V22ManifestTemplate manifestTemplate, String imageTag) {
    this.manifestTemplate = manifestTemplate;
    this.imageTag = imageTag;
  }

  @Override
  public void buildRequest(Request.Builder builder) {
    builder.setContentType(V22ManifestTemplate.MEDIA_TYPE);
    builder.setBody(JsonTemplateMapper.toBlob(manifestTemplate));
  }

  @Override
  public Object handleResponse(Response response) {
    return null;
  }

  @Override
  public String getApiRouteSuffix() {
    return "/manifests/" + imageTag;
  }

  @Override
  public String getHttpMethod() {
    return HttpMethods.PUT;
  }

  @Override
  public String getActionDescription(String serverUrl, String imageName) {
    return "push image manifest for " + serverUrl + "/" + imageName + ":" + imageTag;
  }
}