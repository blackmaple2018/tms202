package com.msemu.world.client.character.quest.act;

import com.msemu.world.client.character.Character;

/**
 * Created by Weber on 2018/4/13.
 */
public interface IQuestAction {
    /**
     * Gives the reward of this QuestReward to a {@link Character}
     * @param chr The Char to give the reward to.
     */
    void giveReward(Character chr);
}
