package kr.ac.postech.sslab.main;

import kr.ac.postech.sslab.extension.*;
import kr.ac.postech.sslab.nft.NFT;
import org.hyperledger.fabric.shim.ChaincodeStub;
import java.util.List;

public class CustomMain extends Main implements IXNFT {
    private Type1NFT type1 = new Type1NFT();
    private Type2NFT type2 = new Type2NFT();

    @Override
    public Response init(ChaincodeStub stub) {
        return super.init(stub);
    }

    @Override
    public Response invoke(ChaincodeStub stub) {
        try {
            String func = stub.getFunction();
            List<String> args = stub.getParameters();

            switch (func) {
                case "mint":
                    return this.mint(stub, args);

                case "setURI":
                    return this.setURI(stub, args);

                case  "getURI":
                    return this.getURI(stub, args);

                case "setXAttr":
                    return this.setXAttr(stub, args);

                case "getXAttr":
                    return this.getXAttr(stub, args);

                default:
                    return super.invoke(stub);
            }

        } catch (Throwable throwable) {
            return newErrorResponse("FAILURE");
        }
    }

    @Override
    public Response mint(ChaincodeStub stub, List<String> args) {
        String type = args.get(1).toLowerCase();
        switch (type) {
            case "type1":
                return this.type1.mint(stub, args);

            case "type2":
                return this.type2.mint(stub, args);

            default:
                return super.mint(stub, args);
        }
    }

    @Override
    public Response getURI(ChaincodeStub stub, List<String> args) {
        try {
            String id = args.get(0).toLowerCase();
            NFT nft = NFT.read(stub, id);
            switch (nft.getType()) {
                case "type1":
                    return this.type1.getURI(stub, args);

                case "type2":
                    return this.type2.getURI(stub, args);

                default:
                    throw new Throwable("FAILURE");
            }
        } catch (Throwable throwable) {
            return newErrorResponse("FAILURE");
        }
    }

    @Override
    public Response setURI(ChaincodeStub stub, List<String> args) {
        try {
            String id = args.get(0).toLowerCase();
            NFT nft = NFT.read(stub, id);
            switch (nft.getType()) {
                case "type1":
                    return this.type1.setURI(stub, args);

                case "type2":
                    return this.type2.setURI(stub, args);

                default:
                    throw new Throwable("FAILURE");
            }
        } catch (Throwable throwable) {
            return newErrorResponse("FAILURE");
        }
    }

    @Override
    public Response setXAttr(ChaincodeStub stub, List<String> args) {
        try {
            String id = args.get(0).toLowerCase();
            NFT nft = NFT.read(stub, id);
            switch (nft.getType()) {
                case "type1":
                    return this.type1.setXAttr(stub, args);

                case "type2":
                    return this.type2.setXAttr(stub, args);

                default:
                    throw  new Throwable("FAILURE");
            }
        } catch (Throwable throwable) {
            return newErrorResponse("FAILURE");
        }
    }

    @Override
    public Response getXAttr(ChaincodeStub stub, List<String> args) {
        try {
            String id = args.get(0).toLowerCase();
            NFT nft = NFT.read(stub, id);
            switch (nft.getType()) {
                case "type1":
                    return this.type1.getXAttr(stub, args);

                case "type2":
                    return this.type2.getXAttr(stub, args);

                default:
                    throw  new Throwable("FAILURE");
            }
        } catch (Throwable throwable) {
            return newErrorResponse("FAILURE");
        }
    }

    public static void main(String[] args) {
        new CustomMain().start(args);
    }
}
