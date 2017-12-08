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

package com.google.cloud.tools.crepecake.image;

import com.google.cloud.tools.crepecake.blob.BlobDescriptor;
import java.security.DigestException;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** Tests for {@link ImageLayers}. */
public class ImageLayersTest {

  private DescriptorDigest fakeDigest;
  private Layer fakeLayer;

  @Before
  public void setUpFakes() throws DigestException {
    fakeDigest =
        DescriptorDigest.fromDigest(
            "sha256:8c662931926fa990b41da3c9f42663a537ccd498130030f9149173a0493832ad");
    fakeLayer = new ReferenceLayer(new BlobDescriptor(1000, fakeDigest), fakeDigest);
  }

  @Test
  public void testAddLayer_success() throws DigestException, ImageException, LayerException {
    List<Layer> expectedLayers = Arrays.asList(fakeLayer);

    ImageLayers imageLayers = new ImageLayers();
    imageLayers.add(fakeLayer);

    Assert.assertThat(imageLayers.asList(), CoreMatchers.is(expectedLayers));
  }

  @Test
  public void testAddLayer_duplicate() throws ImageException, LayerException {
    ImageLayers imageLayers = new ImageLayers();
    imageLayers.add(fakeLayer);

    try {
      imageLayers.add(fakeLayer);
      Assert.fail("Adding duplicate layer should throw ImageException");
    } catch (ImageException ex) {
      Assert.assertEquals("Cannot add the same layer more than once", ex.getMessage());
    }
  }
}
