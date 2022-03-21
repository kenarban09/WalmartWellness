# Walmart Wellness Challenge

1. Fetch a list of countries in JSON format from this URL: https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json

2. Display all the countries in a RecyclerView ordered by the position they appear in the JSON. In each table cell, show the country's "name", "region", "code" and "capital" in this format:

---------------------------------------
|                                     |
| "name", "region"             "code" |
|                                     |
| "capital"                           |
|                                     |
---------------------------------------

For example:

---------------------------------------
|                                     |
| United States of America, NA     US |
|                                     |
| Washington, D.C.                    |
|                                     |
---------------------------------------
|                                     |
| Uruguay, SA                      UY |
|                                     |
| Montevideo                          |
|                                     |
---------------------------------------


The user should be able to scroll thru the entire list of countries.

3. The implementation should be robust (i.e., handle errors and edge cases) and support device rotation.

Limit yourself to whatever time you have. The goal is write high-quality code for the portion you choose to implement, not the number of features implemented.