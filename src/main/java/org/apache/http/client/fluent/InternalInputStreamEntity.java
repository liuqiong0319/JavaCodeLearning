/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.client.fluent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.util.Args;

class InternalInputStreamEntity extends AbstractHttpEntity {

	private final InputStream content;
	private final long length;

	public InternalInputStreamEntity(final InputStream instream, final long length, final ContentType contentType) {
		super();
		this.content = Args.notNull(instream, "Source input stream");
		this.length = length;
		if (contentType != null) {
			setContentType(contentType.toString());
		}
	}

	@Override
	public boolean isRepeatable() {
		return false;
	}

	@Override
	public long getContentLength() {
		return this.length;
	}

	@Override
	public InputStream getContent() throws IOException {
		return this.content;
	}

	@Override
	public void writeTo(final OutputStream outstream) throws IOException {
		Args.notNull(outstream, "Output stream");
		final InputStream instream = this.content;
		try {
			final byte[] buffer = new byte[4096];
			int l;
			if (this.length < 0) {
				// consume until EOF
				while ((l = instream.read(buffer)) != -1) {
					outstream.write(buffer, 0, l);
				}
			} else {
				// consume no more than length
				long remaining = this.length;
				while (remaining > 0) {
					l = instream.read(buffer, 0, (int) Math.min(4096, remaining));
					if (l == -1) {
						break;
					}
					outstream.write(buffer, 0, l);
					remaining -= l;
				}
			}
		} finally {
			instream.close();
		}
	}

	@Override
	public boolean isStreaming() {
		return true;
	}

}
