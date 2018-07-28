package com.msemu.core.network.packets.inpacket;

import com.msemu.commons.network.packets.InPacket;
import com.msemu.core.network.GameClient;
import com.msemu.world.client.character.Character;
import com.msemu.world.client.field.Field;
import com.msemu.world.client.field.runestones.RuneStoneManager;

public class CP_RuneStoneSkillReq extends InPacket<GameClient> {

    private boolean result;
    private int unk1;
    private int unk2;

    public CP_RuneStoneSkillReq(short opcode) {
        super(opcode);
    }

    @Override
    public void read() {
        result = decodeByte() > 0;
        if(result) {
            unk1 = decodeInt();
            unk2 = decodeInt();
        }
    }

    @Override
    public void runImpl() {
        final Character chr = getClient().getCharacter();
        final Field field = chr.getField();
        final RuneStoneManager rsm = field.getRuneStoneManager();
        if (result) {
            rsm.useRuneStone(chr);
        }
    }
}
