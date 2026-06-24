package dao;

import java.util.ArrayList;
import java.util.List;

import dto.KeyValueDto;

public class SituationDao {
	public List<KeyValueDto> select() {
		List<KeyValueDto> result = new ArrayList<>();
		{
			KeyValueDto keyValueDto = new KeyValueDto("1", "出勤時");
			result.add(keyValueDto);
		}
		{
			KeyValueDto keyValueDto = new KeyValueDto("2", "昼休み");
			result.add(keyValueDto);
		}
		{
			KeyValueDto keyValueDto = new KeyValueDto("3", "退勤時");
			result.add(keyValueDto);
		}
		{
			KeyValueDto keyValueDto = new KeyValueDto("4", "休日");
			result.add(keyValueDto);
		}
		{
			KeyValueDto keyValueDto = new KeyValueDto("5", "在宅中");
			result.add(keyValueDto);
		}
		{
			KeyValueDto keyValueDto = new KeyValueDto("6", "その他");
			result.add(keyValueDto);
		}
		return result;
	}

	public KeyValueDto select(String key) {
		KeyValueDto result = null;
		List<KeyValueDto> list = select();
		for (int i = 0; i < list.size(); i++) {
			KeyValueDto keyValueDto = list.get(i);
			String currentKey = keyValueDto.getKey();
			if (currentKey.equals(key)) {
				result = keyValueDto;
				break;
			}
		}
		return result;
	}
}
