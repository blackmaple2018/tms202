package com.msemu.login.client.character.items;

import com.msemu.commons.database.Schema;
import com.msemu.commons.network.packets.OutPacket;
import com.msemu.commons.utils.types.FileTime;
import com.msemu.commons.data.enums.InvType;
import com.msemu.core.network.LoginClient;
import com.msemu.world.constants.ItemConstants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Weber on 2018/4/11.
 */
@Schema
@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private long id;
    @Column(name = "inventoryId")
    @Getter
    @Setter
    protected int inventoryId;
    @Column(name = "itemId")
    @Getter
    @Setter
    protected int itemId;
    @Column(name = "bagIndex")
    @Getter
    @Setter
    protected int bagIndex;
    @Column(name = "cashItemSerialNumber")
    @Getter
    @Setter
    protected long cashItemSerialNumber;
    @JoinColumn(name = "dateExpire")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    protected FileTime dateExpire = FileTime.getFileTimeFromType(FileTime.Type.PERMANENT);
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "invType")
    @Getter
    @Setter
    protected InvType invType;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    @Getter
    @Setter
    protected Type type;
    @Column(name = "isCash")
    @Getter
    @Setter
    protected boolean isCash;
    @Column(name = "quantity")
    @Getter
    @Setter
    protected int quantity;
    @Column(name = "owner")
    @Getter
    @Setter
    private String owner = "";


    public Item() {
    }

    public Item(int itemId, int bagIndex, long cashItemSerialNumber, FileTime dateExpire, InvType invType,
                boolean isCash, Type type) {
        this.itemId = itemId;
        this.bagIndex = bagIndex;
        this.cashItemSerialNumber = cashItemSerialNumber;
        this.dateExpire = dateExpire;
        this.invType = invType;
        this.isCash = isCash;
        this.type = type;
    }

    @Override
    public String toString() {
        return "資料庫編號: " + getId() + ", 道具ID: " + getItemId() + ", 數量: " + getQuantity() + ", 種類: " + getInvType()
                + ", 背包位置: " + getBagIndex();
    }

    public void drop() {
        setBagIndex(0);
    }

    public void addQuantity(int amount) {
        if (amount > 0 && amount + getQuantity() > 0) {
            setQuantity(getQuantity() + amount);
        }
    }

    public void removeQuantity(int amount) {
        if (amount > 0) {
            setQuantity(Math.max(0, getQuantity() - amount));
        }
    }


    public void encode(OutPacket<LoginClient> outPacket) {
        outPacket.encodeByte(getType().getValue());
        // GW_ItemSlotBase
        outPacket.encodeInt(getItemId());
        boolean hasSN = getCashItemSerialNumber() > 0;
        outPacket.encodeByte(hasSN);
        if (hasSN) {
            outPacket.encodeLong(getId());
        }
        getDateExpire().encode(outPacket);
        outPacket.encodeInt(getBagIndex());
        if (getType() == Type.ITEM) {
            outPacket.encodeShort(getQuantity()); // nQuantity
            outPacket.encodeString(getOwner()); // sOwner
            outPacket.encodeShort(0); // flag
            if (ItemConstants.類型.可充值道具(getItemId()) || ItemConstants.類型.isFamiliar(getItemId()) ||
                    getItemId() / 10000 == 302) {
                outPacket.encodeLong(getInventoryId());
            }
            // TODO 萌寵
            outPacket.encodeInt(0); // familiarId
            outPacket.encodeShort(0); // maxLevel
            outPacket.encodeShort(0); // maxLevel
            outPacket.encodeShort(0); // maxLevel
            outPacket.encodeShort(0); // option 1
            outPacket.encodeShort(0); // option 2
            outPacket.encodeShort(0); // option 3
            outPacket.encodeByte(0); // grade
        } else if (getType() == Type.PET) {
            // TODO 寵物
            outPacket.encodeString("", 13); // name
            outPacket.encodeByte(0); // maxLevel
            outPacket.encodeShort(0); // 親密度
            outPacket.encodeByte(0); // 飢餓
            outPacket.encodeFT(new FileTime(-1)); // 期限
            outPacket.encodeShort(0); // nPetAttribute
            outPacket.encodeShort(0); // uPetSkill flag
            outPacket.encodeInt(0); // nRemainLife
            outPacket.encodeByte(0); // nActiveStat
            outPacket.encodeInt(0); // nAutoBuffSkill buff技能
            // nPetHue
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(0);
            outPacket.encodeInt(-1);
            //nGiantRate
            outPacket.encodeShort(100);
        }
    }


    public enum Type {
        EQUIP(1),
        ITEM(2),
        PET(3);
        private byte value;

        Type(byte val) {
            this.value = val;
        }

        Type(int val) {
            this((byte) val);
        }

        public byte getValue() {
            return value;
        }

        public static Type getTypeById(int id) {
            return Arrays.stream(Type.values()).filter(type -> type.getValue() == id).findFirst().orElse(null);
        }
    }

}

