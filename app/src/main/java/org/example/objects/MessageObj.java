package org.example.objects;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class MessageObj {

  String from;
  String to;
  String subject;
  String body;
  File attachment;
}
