# Third-party notice

`tailwind-icons-outline` compiles icon art from [Heroicons](https://github.com/tailwindlabs/heroicons)
(Outline style, 24×24) into Compose `ImageVector` code. The path geometry originates from
that project; this module's own code is the generation pipeline and the resulting
`.kt` files, not hand-authored art.

Heroicons license, reproduced in full per its terms:

```
MIT License

Copyright (c) Tailwind Labs, Inc.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

Source snapshot: `github.com/tailwindlabs/heroicons` `optimized/24/outline/*.svg`,
`master` branch, fetched 2026-07-08. Regenerate via
[`scripts/codegen/generate_icons.py`](../../scripts/codegen/generate_icons.py).
