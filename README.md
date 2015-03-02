# Handlebars.java Math Helpers

A Library of Helpers for performing simple math operations in Handlebars.

Perform math operations in handlebars. Inspired by [this JSFiddle](http://jsfiddle.net/mpetrovich/wMmHS/).

Operands are treated as `java.math.BigDecimal` and operations are performed with the
`java.math.MathContext.DECIMAL64 MathContext`, yielding results with 16 bits of precision
and rounding to the nearest **even** digit, according to IEEE 754R. You can force rounding
decimal results using the extra parameter `scale`, which corresponds to calling
`BigDecimal.setScale(int scale, RoundingMode.HALF_UP)`.

## addition
{{math arg0 "+" arg1}} // arg0 + arg1

## subtraction

```
{{math arg0 "-" arg1}} // arg0 - arg1
```

## multiplication

```
{{math arg0 "*" arg1}} // arg0 * arg1
```

## division

```
{{math arg0 "/" arg1}} // arg0 / arg1
```

## modulus

```
{{math arg0 "%" arg1}} // arg0 % arg1
```
