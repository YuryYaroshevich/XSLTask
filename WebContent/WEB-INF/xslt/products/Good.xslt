<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:pr="http://www.epam.com/products">
	<xsl:output method="html" />

	<!-- match the <good> element -->
	<xsl:template match="pr:good">
		<table class="push-to-right">
			<tr>
				<td>Producer:</td>
				<td><xsl:value-of select="pr:producer" /></td>
			</tr>
			<tr>
				<td>Model:</td>
				<td><xsl:value-of select="pr:model" /></td>
			</tr>
			<tr>
				<td>Date of issue:</td>
				<td><xsl:value-of select="pr:date-of-issue" /></td>
			</tr>
			<tr>
				<td>Color:</td>
				<td><xsl:value-of select="pr:color" /></td>
			</tr>
			<tr>
				<xsl:choose>
					<xsl:when test="pr:price">
						<td>Price:</td>
						<td><xsl:value-of select="pr:price" /></td>
					</xsl:when>
					<xsl:otherwise>
						<td>Not in stock</td>
					</xsl:otherwise>
				</xsl:choose>
			</tr>
		</table>
		<br></br>
	</xsl:template>
</xsl:stylesheet>
