package com.tjn.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sprinkler {
    public Sprinkler makeCopy() {
        return new Sprinkler(this.id, this.forestName, this.state, this.cutOffThreshold, this.threshold);
    }

    private Integer id;
    private String forestName;
    private Boolean state;
    private Double cutOffThreshold;
    private Double threshold;
}
