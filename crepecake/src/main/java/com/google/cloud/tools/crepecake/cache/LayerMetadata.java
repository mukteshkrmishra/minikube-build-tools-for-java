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

package com.google.cloud.tools.crepecake.cache;

import com.google.common.annotations.VisibleForTesting;
import java.util.List;
import javax.annotation.Nullable;

/** Metadata about a layer stored in the cache. This is part of the {@link CacheMetadata}. */
class LayerMetadata {

  /** The type of layer. */
  private final CachedLayerType type;

  /**
   * A list of image tags where the layer should exist at (i.e. the layer was pushed to the
   * repository for that image).
   */
  private final List<String> existsOn;

  /** The paths to the source directories that the layer was constructed from. */
  @Nullable private List<String> sourceDirectories;

  /** The last time the layer was constructed, or negative if unknown. */
  private long lastModifiedTime;

  LayerMetadata(
      CachedLayerType type,
      List<String> existsOn,
      @Nullable List<String> sourceDirectories,
      long lastModifiedTime) {
    this.type = type;
    this.existsOn = existsOn;
    this.sourceDirectories = sourceDirectories;
    this.lastModifiedTime = lastModifiedTime;
  }

  CachedLayerType getType() {
    return type;
  }

  List<String> getExistsOn() {
    return existsOn;
  }

  List<String> getSourceDirectories() {
    return sourceDirectories;
  }

  public long getLastModifiedTime() {
    return lastModifiedTime;
  }

  @VisibleForTesting
  void setSourceDirectories(List<String> sourceDirectories) {
    this.sourceDirectories = sourceDirectories;
  }
}