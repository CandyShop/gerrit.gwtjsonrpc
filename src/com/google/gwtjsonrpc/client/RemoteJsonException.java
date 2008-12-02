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

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Exception given to {@link AsyncCallback#onFailure(Throwable)}.
 * <p>
 * This exception is used if the remote JSON server has returned a well-formed
 * JSON error response. The message from that response is the message of the
 * exception, and other details are left out.
 */
public class RemoteJsonException extends Exception {
  public RemoteJsonException(final String message) {
    super(message);
  }
}
