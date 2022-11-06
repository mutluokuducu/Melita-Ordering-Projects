<!DOCTYPE html>
<html>

<head>
	<title>Welcome Letter</title>
</head>
<style>
table,
th,
td {
	border: 1px solid black;
}
</style>

<body>
	<h1>Personal details:</>
	<H2>Id: ${id}</h2>
	<H2>Full Name: ${fullName}</h2>
	<H2>Phone Number: ${phoneNumber}</h2>
	<H2>Email address: ${emailAddress}</h2>
</br>
<h1>Installation Address:</h1>
	<H2>Address: ${addressLine}</h2>
	<H2>Post Code: ${postCode}</h2>
	<H2>City: ${city}</h3>
	<H2>Country: ${country}</h2>
	<h1>Available Slots :</h1>
	<table style="width:60%">
		<tr>
			<td>installationDate</td>
			<td>startTime</td>
			<td>endTime</td>
		</tr>
		<tr>
			<td>
				<ol>
					<#list installationDate as date>
						<li>${date}</li>
					</#list>
				</ol>
			</td>
			<td>
				<ul>
					<#list startTime as start>
						<li>${start}</li>
					</#list>
				</ul>
			</td>
			<td>
				<ul>
					<#list endTime as end>
						<li>${end}</li>
					</#list>
				</ul>
			</td>
		</tr>
	</table>
	<h1>Packages List :</h1>
	<table style="width:60%">
		<tr>
			<td>productsName</td>
			<td>packageName</td>
		</tr>
		<tr>
			<td>
				<ol>
					<#list productsName as product>
						<li>${product}</li>
					</#list>
				</ol>
			</td>
			<td>
				<ul>
					<#list packageName as package>
						<li>${package}</li>
					</#list>
				</ul>
			</td>
		</tr>
	</table>
</body>

</html>