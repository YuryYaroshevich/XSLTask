<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:pr="http://www.epam.com/products">
	<xsl:output method="html" />

	<!-- match the <good> element -->
	<xsl:template match="pr:good">
		<tr>
			<td>
				<xsl:value-of select="pr:producer" />
			</td>
			<td>
				<xsl:value-of select="pr:model" />
			</td>
			<td>
				<xsl:value-of select="pr:date-of-issue" />
			</td>
			<td>
				<xsl:value-of select="pr:color" />
			</td>
			<xsl:choose>
				<xsl:when test="pr:price">
					<td>
						<xsl:value-of select="pr:price" />
					</td>
				</xsl:when>
				<xsl:otherwise>
					<td>Not in stock</td>
				</xsl:otherwise>
			</xsl:choose>
		</tr>
	</xsl:template>
</xsl:stylesheet>
