<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:pr="http://www.epam.com/products">

	<xsl:include href="good.xslt" />

	<xsl:output method="html" />

	<xsl:param name="categoryName" />
	<xsl:param name="subcategoryName" />

	<xsl:template match="/pr:products">
		<html>
			<head>
				<link rel="stylesheet" href="css/pushToRight.css" type="text/css"
					media="screen" />
				<link rel="stylesheet" href="css/goods.css" type="text/css"
					media="screen" />
				<title>
					Goods of
					<xsl:value-of select="$subcategoryName" />
				</title>
			</head>
			<body>
				<h1>
					Goods of
					<xsl:value-of select="$subcategoryName" />
				</h1>
				<table class="push-to-right">
					<tr>
						<th>Producer</th>
						<th>Model</th>
						<th>Date of issue</th>
						<th>Color</th>
						<th>Price</th>
					</tr>
					<xsl:apply-templates
						select="pr:category[@name=$categoryName]/
					        pr:subcategory[@name=$subcategoryName]/pr:good" />
				</table>
				<div id="control-buttons">
					<form action="controller?command=SUBCATEGORIES" method="POST">
						<input type="hidden" name="categoryName" value="{$categoryName}" />
						<input type="submit" value="BACK" class="push-to-right" />
					</form>
					<form action="controller" method="POST">
						<input type="hidden" name="command" value="ADD_GOOD" />
						<input type="hidden" name="categoryName" value="{$categoryName}" />
						<input type="hidden" name="subcategoryName" value="{$subcategoryName}" />
						<input class="push-to-right" type="submit" value="ADD" />
					</form>
				</div>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>