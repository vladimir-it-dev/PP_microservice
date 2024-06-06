package com.bank.antifraud.config;

import org.hibernate.boot.model.naming.*;

public class PluralImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

    @Override
    public Identifier determinePrimaryTableName(ImplicitEntityNameSource source) {
        Identifier identifier = super.determinePrimaryTableName(source);
        return Identifier.toIdentifier(identifier.getText() + "s");
    }
}
