package com.gmail.berndivader.mythicskript.expressions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class ExprGetActiveMobs extends SimpleExpression<ActiveMob>{
	private Expression<String> worldString;
	private boolean all;

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends ActiveMob> getReturnType() {
		return ActiveMob.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean var3, ParseResult var4) {
		
		if (matchedPattern==0) {
			worldString = (Expression<String>) expr[0];
			all = false;
		} else {
			all = true;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event var1, boolean var2) {
		return null;
	}

	@Override
	@Nullable
	protected ActiveMob[] get(Event e) {
		List<ActiveMob> ams = new ArrayList<ActiveMob>();
		if (all) {
			ams.addAll(MythicMobs.inst().getMobManager().getActiveMobs());
		} else {
			String world = worldString.getSingle(e).toLowerCase();
			for (ActiveMob am : MythicMobs.inst().getMobManager().getActiveMobs()) {
				if (am.getEntity().getWorld().getName().toLowerCase().equals(world)) {
					ams.add(am);
					continue;
				}
			}
		}
		return ams.toArray(new ActiveMob[0]);
	}
}
