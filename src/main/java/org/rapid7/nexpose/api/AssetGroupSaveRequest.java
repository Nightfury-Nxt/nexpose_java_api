/**
 * Copyright (C) 2010, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *    * Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *    * Neither the name of the <organization> nor the
 *      names of its contributors may be used to endorse or promote products
 *      derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.rapid7.nexpose.api;

import org.rapid7.nexpose.api.APISession.APISupportedVersion;
import org.rapid7.nexpose.api.generators.IContentGenerator;

/**
 * Represents the AssetGroupSaveRequest NeXpose API request.
 *
 * @author Leonardo Varela
 */
public class AssetGroupSaveRequest extends TemplateAPIRequest
{
   /////////////////////////////////////////////////////////////////////////
   // Public methods
   /////////////////////////////////////////////////////////////////////////

   /**
    * Creates a new AssetGroupSaveRequest NeXpose API request.
    *
    * @param sessionId the session to be used if different from the one on the
    *        current APISession. useful when testing edge cases and testing in
    *        general.
    * @param syncId the syncId to identify the request/response pair.
    * @param groupId the id of the group to save (-1) if you want to create a
    *        new one.
    * @param groupName the name of the group to save.
    * @param groupDescription the description of the group to save.
    * @param groupRiskScore the risk score associated with the group to save.
    * @param devicesGenerator the {@link IContentGenerator} that knows how to
    *        include devices inside of the AssetGroupSaveRequest.
    */
   public AssetGroupSaveRequest(
      String sessionId,
      String syncId,
      String groupId,
      String groupName,
      String groupDescription,
      String groupRiskScore,
      IContentGenerator devicesGenerator)
   {
      super(sessionId, syncId);
      set("assetGroupId", groupId);
      set("assetGroupName", groupName);
      set("assetGroupDescription", groupDescription);
      set("assetGroupRiskScore", groupRiskScore);
      set("devicesGenerator", devicesGenerator);
      m_firstSupportedVersion = APISupportedVersion.V1_0;
      m_lastSupportedVersion = APISupportedVersion.V1_1;
   }
}
