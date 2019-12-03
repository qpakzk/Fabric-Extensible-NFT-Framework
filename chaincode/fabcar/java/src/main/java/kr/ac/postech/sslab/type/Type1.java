package kr.ac.postech.sslab.type;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Type1 implements IType {
    private String subAttr1;
    private String subAttr2;
    private String subAttr3;

    @Override
    public void assign(ArrayList<String> args) {
        this.subAttr1 = args.get(0);
        this.subAttr2 = args.get(1);
        this.subAttr3 = args.get(2);
    }

    @Override
    public void assign(Map<String, Object> map) {
        this.subAttr1 = (String) map.get("subAttr1");
        this.subAttr2 = (String) map.get("subAttr2");
        this.subAttr3 = (String) map.get("subAttr3");
    }

    @Override
    public void setXAttr(String index, String value) {
        switch (index) {
            case "subAttr1":
                this.subAttr1 = value;
                break;

            case "subAttr2":
                this.subAttr2 = value;
                break;

            case "subAttr3":
                this.subAttr3 = value;
                break;
        }
    }

    @Override
    public String getXAttr(String index) {
        switch (index) {
            case "subAttr1":
                return this.subAttr1;

            case "subAttr2":
                return this.subAttr2;

            case "subAttr3":
                return this.subAttr3;
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
        map.put("subAttr2", this.subAttr2);
        map.put("subAttr3", this.subAttr3);

        return map;
    }
}