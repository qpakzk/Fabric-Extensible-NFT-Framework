package kr.ac.postech.sslab.extension;

import kr.ac.postech.sslab.adapter.XAttr;
import kr.ac.postech.sslab.nft.NFT;
import kr.ac.postech.sslab.type.URI;
import kr.ac.postech.sslab.user.Address;
import org.hyperledger.fabric.shim.ChaincodeStub;

import java.util.ArrayList;
import java.util.List;

public class Type2NFT extends XNFT {
    @Override
    public Response mint(ChaincodeStub stub, List<String> args) {
        try {
            /*
             * customize the number of arguments
             * The number of arguments should be more than 3
             * to initialize standard attributes (id, type, owner).
             * Last two attributes are off-chain sub attributes (path, merkle root).
             */
            int num_of_args = 6;
            if (args.size() != num_of_args) {
                throw new Throwable("FAILURE");
            }

            String id = args.get(0);
            String type = args.get(1);
            String owner = args.get(2);
            String subAttr1 = args.get(3);
            String path = args.get(4);
            String merkleroot = args.get(5);

            String caller = Address.getMyAddress(stub);
            if (!caller.equals(owner))
                throw new Throwable();

            XAttr xattr = new XAttr();
            ArrayList<String> params = new ArrayList<>();
            params.add(subAttr1);

            xattr.assign(type, params);

            URI uri = new URI(path, merkleroot);

            NFT nft = new NFT();
            nft.mint(stub, id, type, owner, xattr, uri);

            return newSuccessResponse("SUCCESS");
        } catch (Throwable throwable) {
            return newErrorResponse("FAILURE");
        }
    }
}
