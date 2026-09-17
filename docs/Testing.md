# Testing Checklist

| Test | Expected Result |
|---|---|
| New registration | Student is saved |
| Duplicate registration | Rejected |
| Correct login | Dashboard opens |
| Wrong password | Login rejected |
| Empty required field | Input rejected |
| Invalid slot group | Rejected |
| Browse with no resources | No resources message |
| Add important question/topic/instruction | Saved successfully |
| Search by subject | Matching resources shown |
| Search by exam and slot group | Matching resources shown |
| Upload valid file | File copied to uploads/ and metadata saved |
| Upload missing file | Error shown without crashing |
| My Uploads | Current user's resources shown |
| Delete own resource | Resource deleted |
| Delete another user's resource | Deletion rejected |
| Restart program | Saved metadata remains |
