<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:pr="http://www.epam.com/products">

	<xsl:output method="html" />

	<xsl:param name="categoryName" />
	<xsl:param name="subcategoryName" />

	<xsl:template name="addGood" match="/">
		<html>
			<head>
			    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
				<title>
					Add good
				</title>
			</head>
			<body>
				<h2>
					Add good in
					<xsl:value-of select="$subcategoryName" />
					subcategory
				</h2>
				<form action="controller">
					<input type="hidden" name="command" value="SAVE_GOOD" />
					<input type="hidden" name="categoryName" value="{$categoryName}" />
					<input type="hidden" name="subcategoryName" value="{$subcategoryName}" />
					<table>
						<tr>
							<td>Producer:</td>
							<td>
								<input type="text" name="producer" />
							</td>
						</tr>
						<tr>
							<td>Model(LLNNN, L - letter, N - number):</td>
							<td>
								<input type="text" name="model" />
							</td>
						</tr>
						<tr>
							<td>Date of issue(dd-MM-YYYY):</td>
							<td>
								<input type="text" name="dateOfIssue" />
							</td>
						</tr>
						<tr>
							<td>Color:</td>
							<td>
								<input type="text" name="color" />
							</td>
						</tr>
						<tr>
							<td>Price</td>
							<td>
								<input type="text" name="price" />
							</td>
						</tr>
						<tr>
							<td>Not in stock</td>
							<td>
								<input type="checkbox" name="notInStock" value="true" />
							</td>
						</tr>
					</table>
					<input type="submit" value="SAVE GOOD" />
				</form>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>