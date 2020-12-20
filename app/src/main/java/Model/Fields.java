package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Fields  implements Serializable {

 private Priority priority;
 private Date created;
 private Date updated;
 private Reporter reporter;
 private Status status;
 private List<String> labels;
 private Description description;
 private Creator creator;
 private Comment comment;
 private Issuetype issuetype;
 private Assignee assignee;

 public Description getDescription() {
  return description;
 }

 public void setDescription(Description description) {
  this.description = description;
 }

 public Priority getPriority() {
  return priority;
 }

 public void setPriority(Priority priority) {
  this.priority = priority;
 }

 public Date getCreated() {
  return created;
 }

 public void setCreated(Date created) {
  this.created = created;
 }

 public Reporter getReporter() {
  return reporter;
 }

 public void setReporter(Reporter reporter) {
  this.reporter = reporter;
 }

 public Status getStatus() {
  return status;
 }

 public void setStatus(Status status) {
  this.status = status;
 }

 public List<String> getLabels() {
  return labels;
 }

 public void setLabels(List<String> labels) {
  this.labels = labels;
 }

 public Date getUpdated() {
  return updated;
 }

 public void setUpdated(Date updated) {
  this.updated = updated;
 }

 public Creator getCreator() {
  return creator;
 }

 public void setCreator(Creator creator) {
  this.creator = creator;
 }

 public Comment getComment() {
  return comment;
 }

 public void setComment(Comment comment) {
  this.comment = comment;
 }

 public Issuetype getIssuetype() {
  return issuetype;
 }

 public void setIssuetype(Issuetype issuetype) {
  this.issuetype = issuetype;
 }

 public Assignee getAssignee() {
  return assignee;
 }

 public void setAssignee(Assignee assignee) {
  this.assignee = assignee;
 }

 @Override
 public String toString() {
  return "Fields{" +
          "priority=" + priority +
          ", created=" + created +
          ", updated=" + updated +
          ", reporter=" + reporter +
          ", status=" + status +
          ", labels=" + labels +
          ", description=" + description +
          ", creator=" + creator +
          ", comment=" + comment +
          ", issuetype=" + issuetype +
          ", assignee=" + assignee +
          '}';
 }
}
