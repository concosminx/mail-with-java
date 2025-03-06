package org.example.objects;

import java.io.File;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageObj {

  String from;
  String to;
  String subject;
  String body;
  File attachment;
}
