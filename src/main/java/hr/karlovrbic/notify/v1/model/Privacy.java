package hr.karlovrbic.notify.v1.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by thekarlo95 on 10/30/16.
 */
@XmlType
@XmlEnum(Integer.class)
public enum Privacy {

    @XmlEnumValue("1")PRIVATE("1"),
    @XmlEnumValue("2")PUBLIC("2");

    private String num;

    Privacy(String num) {
        this.num = num;
    }

    public static Privacy fromString(String num) {
        Privacy out = null;

        if (num != null) {
            for (Privacy privacy : Privacy.values()) {
                if (num.equals(privacy.num)) {
                    out = privacy;
                    break;
                }
            }
        }

        return out;
    }

    public String toNumber() {
        return num;
    }
}
