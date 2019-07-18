package kr.ac.postech.sslab.protocol;

import org.hyperledger.fabric.shim.Chaincode.Response;
import org.hyperledger.fabric.shim.ChaincodeStub;
import java.util.List;

public interface FabNFTInterface {
    public Response balanceOf(ChaincodeStub stub, List<String> args);
    public Response ownerOf(ChaincodeStub stub, List<String> args);
    public Response transferFrom(ChaincodeStub stub, List<String> args);
    public Response approve(ChaincodeStub stub, List<String> args);
}