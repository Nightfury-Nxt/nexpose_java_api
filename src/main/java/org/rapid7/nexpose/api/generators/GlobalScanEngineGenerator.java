/**
 * Copyright (C) 2012, Rapid7 LLC, Boston, MA, USA.
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
package org.rapid7.nexpose.api.generators;

import org.rapid7.nexpose.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Global scan engine generator.
 *
 * @author Christopher Lee.
 */
public class GlobalScanEngineGenerator implements IContentGenerator
{
   /////////////////////////////////////////////////////////////////////////
   // Public methods
   /////////////////////////////////////////////////////////////////////////

   /**
    * Constructs the generator.
    */
   public GlobalScanEngineGenerator()
   {
      m_scanEngines = new ArrayList<String>();
   }

   @Override
   public String toString()
   {
      final StringBuilder sb = new StringBuilder("<GlobalScanEngines>");
      for(String template : m_scanEngines)
      {
         sb.append("<GlobalScanEngine name=\"");
         sb.append(StringUtils.xmlEscape(template) + "\">");
         sb.append("</GlobalScanEngine>");
      }
      sb.append("</GlobalScanEngines>");
      return sb.toString();
   }

   @Override
   public void setContents(Element contents)
   {
      try
      {
         final NodeList engines =
            (NodeList) XPathFactory.newInstance().newXPath().evaluate("GlobalScanEngine", contents, XPathConstants.NODESET);
         final int count = engines.getLength();
         for (int i = 0; i < count; i++)
         {
            Element element = (Element)engines.item(i);
            String engine = element.getTextContent();
            m_scanEngines.add(engine);
         }
      }
      catch (XPathExpressionException e)
      {
         System.out.println("Could not parse the generator for GlobalScanEngineGenerator: " + e.toString());
         throw new RuntimeException("The GlobalScanEngine could not be generated: " + e.toString());
      }
   }
   /**
    * Retrieves a list of scan engines to generate.
    *
    * @return A list of the scan engines to be generated.
    */
   public List<String> getScanEngines()
   {
      return m_scanEngines;
   }

   /**
    * Sets the list of scan engines to be generated.
    *
    * @param engines scan engines to be generated.
    */
   public void setScanEngines(List<String> engines)
   {
      m_scanEngines = engines;
   }
   /////////////////////////////////////////////////////////////////////////
   // Non-Public fields
   /////////////////////////////////////////////////////////////////////////

   /**The engines associated with the Silo Config*/
   private List<String> m_scanEngines;
}
