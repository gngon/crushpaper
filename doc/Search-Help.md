<!---
Copyright 2015 CrushPaper.com.

This file is part of CrushPaper.

CrushPaper is free software: you can redistribute it and/or modify
it under the terms of version 3 of the GNU Affero General Public
License as published by the Free Software Foundation.

CrushPaper is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with CrushPaper.  If not, see <http://www.gnu.org/licenses/>.
--->

Here is a description of the syntax:
<br>

### Search Terms
1. You can search for a **literal** or **wilcard** word.
1. The search is case insensitive.
1. The search is accent insensitive.
1. If you search for "term1 term2" then results that contain term1 and term2 will be displayed.
1. AND is then the default operator.
1. You can use OR, AND, and NOT operator.
1. OR mean SHOULD be present.
1. AND mean MUST be present.
1. NOT mean MUST NOT be present.
1. The syntax is [operator] term [[oprator] term [operator term...]...].
1. When the operator is missing in front of a term the default one is AND
1. "term1 OR term2" mean : MUST term1 SHOULD term2. So term2 will be ignored
1. "OR term1 OR term2" mean : SHOULD term1 SHOULD term2
1. "term1 NOT term2" mean : MUST term1 MUST NOT term2
1. OR operator could be replace by : or, |, or ||.
1. AND operator could be replace by : and, &, or &&.
1. NOT operator could be replace by : not, !, or !=.

### Notes
1. Search through the note text of your notes and quotations that have notes.
 
### Quotations
1. Search through the quotation text of your quotations.

### Sources
1. Search through the titles of your sources.
1. Type the URL of a source with its "http" prefix to search by the exact URL of the source.

### Notebooks
1. Search through the title text of your notebooks.
