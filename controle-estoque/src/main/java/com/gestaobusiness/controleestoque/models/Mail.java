package com.gestaobusiness.controleestoque.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Mail {
    String to;
    String subject;
    String body;
}
