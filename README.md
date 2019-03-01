# Simple license key generator

License key generator and license key validator created in Scala for a fictitious application. 

## Description 

The license key generator generates a license key from two pieces of information:
- domain name, like 'company.com'
- expiration date

The license key generator generates a license key that is a single string. The above information are encoded in the key.

The licence key validator takes a license key as input and print out a message:
- 'valid' if the license key is valid for the current time and hostname of the machine it is running on
- 'invalid' if it is running on a machine with an invalid hostname or the license key has expired

## Build
`sbt assembly`

## Generate key

`$ ./generate-key <hostname> <expiration-date>`

## Validate key

`$ ./validate-key <key>`

## Errors

### Key generation
`Error: Invalid date format!`  - Input date cannot be parsed as a date.

### Key validation
`Error: Key parsing error!` - Parsed key is damaged.

`Error: Invalid key format!` - Provided key cannot be parsed. 
