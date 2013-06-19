<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:pr="http://www.epam.com/products">
	<xsl:output method="html" />
	
	<!-- match the <good> element -->
	<xsl:template match="pr:good">
		<ul>
			<li>
				Producer:
				<xsl:value-of select="pr:producer" />
			</li>
			<li>
				Model:
				<xsl:value-of select="pr:model" />
			</li>
			<li>
				Date of issue:
				<xsl:value-of select="pr:date-of-issue" />
			</li>
			<li>
				Color:
				<xsl:value-of select="pr:color" />
			</li>
			<li>
				<xsl:choose>
					<xsl:when test="pr:price">
						Price:
						<xsl:value-of select="pr:price" />
					</xsl:when>
					<xsl:otherwise>
						Not in stock
					</xsl:otherwise>
				</xsl:choose>
			</li>
		</ul>
	</xsl:template>
</xsl:stylesheet>
