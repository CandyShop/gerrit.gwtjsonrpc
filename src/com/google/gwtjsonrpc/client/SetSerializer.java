// Copyright 2008 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.gwtjsonrpc.client;

import com.google.gwt.core.client.JavaScriptObject;

import java.util.HashSet;

/**
 * Serialization for a {@link java.util.Set}.
 * <p>
 * When deserialized from JSON the List implementation is always a
 * {@link HashSet}. When serializing to JSON any Set is permitted.
 */
public class SetSerializer<T> extends JsonSerializer<java.util.Set<T>> {
  private final JsonSerializer<T> serializer;

  public SetSerializer(final JsonSerializer<T> s) {
    serializer = s;
  }

  @Override
  public void printJson(final StringBuffer sb, final java.util.Set<T> o) {
    if (o != null) {
      sb.append('[');
      boolean first = true;
      for (final T item : o) {
        if (first) {
          first = false;
        } else {
          sb.append(',');
        }
        serializer.printJson(sb, item);
      }
      sb.append(']');
    } else {
      sb.append(JS_NULL);
    }
  }

  @Override
  public java.util.Set<T> fromJson(final Object o) {
    if (o == null) {
      return null;
    }

    final JavaScriptObject jso = (JavaScriptObject) o;
    final int n = size(jso);
    final HashSet<T> r = new HashSet<T>(n);
    for (int i = 0; i < n; i++) {
      r.add(serializer.fromJson(get(jso, i)));
    }
    return r;
  }

  private static final native int size(JavaScriptObject o)/*-{ return o.length; }-*/;

  private static final native JavaScriptObject get(JavaScriptObject o, int i)/*-{ return o[i]; }-*/;
}
