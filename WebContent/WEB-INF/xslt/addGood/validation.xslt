<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:pr="http://www.epam.com/products"
	xmlns:valid="xalan://com.epam.xsl.util.GoodValidator"
	extension-element-prefixes="valid">

	<xsl:include href="saveGood.xslt" />
	<xsl:include href="addGood.xslt" />

	<xsl:param name="categoryName" />
	<xsl:param name="subcategoryName" />
	<xsl:param name="producer" />
	<xsl:param name="model" />
	<xsl:param name="dateOfIssue" />
	<xsl:param name="color" />
	<xsl:param name="price" />
	<xsl:param name="notInStock" />

	<xsl:param name="msgAboutProducer" select="valid:validateProducer($producer)" />
	<xsl:param name="msgAboutModel" select="valid:validateModel($model)" />
	<xsl:param name="msgAboutDate" select="valid:validateDate($dateOfIssue)" />
	<xsl:param name="msgAboutColor" select="valid:validateColor($color)" />
	<xsl:param name="msgAboutShopState"
		select="valid:validateShopState($price, $notInStock)" />
	<xsl:param name="isGoodValid"
		select="valid:isGoodValid($msgAboutProducer, $msgAboutModel,
		        $msgAboutDate, $msgAboutColor, $msgAboutShopState)" />

	<xsl:template match="/products">
		<xsl:choose>
			<xsl:when test="$isGoodValid = 'true'">
				<xsl:call-template name="saveGood" />
			</xsl:when>
			<!-- <xsl:otherwise>
				<xsl:call-template name="addGood" />
			</xsl:otherwise> -->
		</xsl:choose>
	</xsl:template>

</xsl:stylesheet>