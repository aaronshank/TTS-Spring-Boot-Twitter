package com.tts.techtalenttwitter.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tweet {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "tweet_id")
  private Long id;

  // This user field--we aren't actually store
  // an entire inside a column of the Tweet database table.
  // instead we actually just want to store in our
  // database a user_id -- which will be the foreign key
  // we can use look up a user.

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  // We are gonna add annotations that tell
  // SPRING BOOT what is and isn't a valid message.
  @NotEmpty(message = "Tweet cannot be empty")
  @Length(max = 280, message = "Tweet cannot have more than 280 characters")
  private String message;

  @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(name = "tweet_tag", joinColumns = @JoinColumn(name = "tweet_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private List<Tag> tags;

  @CreationTimestamp
  private Date createdAt;

}