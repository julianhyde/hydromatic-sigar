/*
 * Licensed to Julian Hyde under one or more contributor license
 * agreements.  See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Julian Hyde licenses this file to you under the Apache
 * License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License.  You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.  See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package net.hydromatic.sigar;

import java.net.URL;
import java.util.function.Function;

/** Libraries (DLLs and Shared Object files) for Hyperic Sigar. */
public enum SigarLibraries {
  INSTANCE;

  public static final String SIGAR_PATH = "org.hyperic.sigar.path";

  public static final String SIGAR_LIB_PATH =
      "/hyperic-sigar-1.6.4/sigar-bin/lib/";

  /** Returns the directory in which the binaries reside, as a URL. */
  public URL resourceUrl() {
    return SigarLibraries.class.getResource(SIGAR_LIB_PATH);
  }

  /** Loads the library, if it is not already loaded. */
  public boolean load(Function<URL, String> resolver) {
    if (!isEmpty(System.getProperty(SIGAR_PATH))) {
      return false;
    }
    final URL sigarPathUrl = resourceUrl();
    final String path = resolver.apply(sigarPathUrl);
    System.setProperty(SIGAR_PATH, path);
    return true;
  }

  private boolean isEmpty(String s) {
    return s == null || s.equals("");
  }
}

// End SigarLibraries.java
