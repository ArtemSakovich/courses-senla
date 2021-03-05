package com.company;

public class Main {

    public static void main(String[] args) {
        AssembleLine assembleLine = new AssembleLine();

	    FirstPartBuilder firstPartBuilder = new FirstPartBuilder();
	    SecondPartBuilder secondPartBuilder = new SecondPartBuilder();
	    ThirdPartBuilder thirdPartBuilder = new ThirdPartBuilder();

	    assembleLine.assembleProduct(firstPartBuilder.buildProductPart(), secondPartBuilder.buildProductPart(),
                                                                            thirdPartBuilder.buildProductPart());
    }
}
