grammar Sets;
expr: expr OP_ADD term | term;
term: term OP_MUL factor | factor;
factor: set | '(' expr ')';
OP_ADD: 'u' | '/';
OP_MUL: 'n';
set: '{' ints '}' | '{' '}';
ints: INT ',' ints | INT;
INT: [0-9]+ ;
