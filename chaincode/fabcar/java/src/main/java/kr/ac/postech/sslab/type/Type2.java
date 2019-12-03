package kr.ac.postech.sslab.type;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Type2 implements IType {
    private String subAttr1;

    @Override
    public void assign(ArrayList<String> args) {
        this.subAttr1 = args.get(0);
    }

    @Override
    public void assign(Map<String, Object> map) {
        this.subAttr1 = (String) map.get("subAttr1");
    }

    @Override
    public void setXAttr(String index, String value) {
        switch (index) {
            case "subAttr1":
                this.subAttr1 = value;
                break;
        }
    }

    @Override
    public String getXAttr(String index) {
        switch (index) {
            case "subAttr1":
                return this.subAttr1;
        }

        return null;
    }

    @Override
    public String toJSONString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this.toMap());
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("subAttr1", this.subAttr1);

        return map;
    }
}